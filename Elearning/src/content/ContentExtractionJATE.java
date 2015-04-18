package content;

//import cc.mallet.util.*;
import java.util.*;

import uk.ac.shef.dcs.oak.jate.core.algorithm.*;
import uk.ac.shef.dcs.oak.jate.core.feature.*;
import uk.ac.shef.dcs.oak.jate.core.feature.indexer.GlobalIndexBuilderMem;
import uk.ac.shef.dcs.oak.jate.core.feature.indexer.GlobalIndexMem;
import uk.ac.shef.dcs.oak.jate.core.npextractor.CandidateTermExtractor;
import uk.ac.shef.dcs.oak.jate.core.npextractor.NounPhraseExtractorOpenNLP;
import uk.ac.shef.dcs.oak.jate.core.npextractor.WordExtractor;
import uk.ac.shef.dcs.oak.jate.model.CorpusImpl;
import uk.ac.shef.dcs.oak.jate.test.AlgorithmTester;
import uk.ac.shef.dcs.oak.jate.util.control.*;
import uk.ac.shef.dcs.oak.jate.util.counter.TermFreqCounter;
import uk.ac.shef.dcs.oak.jate.util.counter.WordCounter;

public class ContentExtractionJATE {
    public void run(String fileName, String reference_corpus_path, String output_path) throws Exception {
    	
	   //[corpus_path]: input folder containing documents (raw text) you want to process
	   //[reference_corpus_path]: required *only* by some algorithms (GlossEx, Weirdness and TermEx). Point to a single file that contains
	   //              frequency statistics of words found in a reference corpus. For the requirement format, see an example in the distribution:
	   //              "nlp_resources/bnc_unifrqs.normal".
	   //[output_folder]: where you want the output to be written to.

    	//if (args.length < 3) System.out.println("Usage: java AlgorithmTester [corpus_path] [reference_corpus_path] [output_folder]");
	  
      try {
         System.out.println(new Date());

         //##########################################################
         //#         Step 1. Extract candidate terms/words from     #
         //#         documents, and index the terms/words, docs     #
         //#         and their relations (occur-in, containing)     #
         //##########################################################

         //stop words and lemmatizer are used for processing the extraction of candidate terms
         //(used by CandidateTermExtractor, see below)
         StopList stop = new StopList(true);
         Lemmatizer lemmatizer = new Lemmatizer();

         //Use an instance of CandidateTermExtractor to extract candidate terms. 
         //Three types of CandidateTermExtractorare implemented. Choose the most appropriate one for your needs.
         //1. An OpenNLP noun phrase extractor that extracts noun phrases as candidate terms. If terms in your
         //   domain are mostly NPs, use this one. If OpenNLP isnt suitable for your domain, you may plug-in
         //   your own NLP tools.
         CandidateTermExtractor npextractor = new NounPhraseExtractorOpenNLP(stop, lemmatizer);

         //2. A generic N-gram extractor that extracts n(default is 5, see the property file) grams. This doesn't
         //   depend on tagging or parsing, but generates more much more candidates than NounPhraseExtractorOpenNLP
         //CandidateTermExtractor npextractor = new NGramExtractor(stop, lemmatizer);
         
         //3. A word extractor that extracts single words as candidate terms. If you task extracts single words as terms,
         //   you could use this instead. 
         //CandidateTermExtractor wordextractor = new WordExtractor(stop, lemmatizer);

         //An instance of WordExtractor is also needed to build word frequency data, which are required by some algorithms.
         //See javadoc for regarding the parameters for the constructor.
         CandidateTermExtractor wordextractor = new WordExtractor(stop, lemmatizer, false, 1);

         //Next start building candidate term and word data and index them.
         GlobalIndexBuilderMem builder = new GlobalIndexBuilderMem();
         GlobalIndexMem wordDocIndex = builder.build(new CorpusImpl(fileName), wordextractor);
         GlobalIndexMem termDocIndex = builder.build(new CorpusImpl(fileName), npextractor);

         //Optionally, you can save the index data as HSQL databases on file system
         //GlobalIndexWriterHSQL.persist(wordDocIndex, "D:/output/worddb");
         //GlobalIndexWriterHSQL.persist(termDocIndex, "D:/output/termdb");


         //##########################################################
         //#         Step 2. Build various statistical features     #
         //#         used by term extraction algorithms. This will  #
         //#         need the indexes built above, and counting the #
         //#         frequencies of terms                           #
         //##########################################################

         //A WordCounter instance is required to count number of words in corpora/documents. This
         //will be used as features by some algorithms.
         WordCounter wordcounter = new WordCounter();

         //Next we need to count frequencies of candidate terms. There are different kinds of term frequency
         // data that are required by different algorithms. In JATE these are called (statistical) "features",
         // see "uk.ac.shef.dcs.oak.jate.core.feature.AbstractFeature" and its implementations.
         //These features must be built using a feature builder, see "uk.ac.shef.dcs.oak.jate.core.feature.AbstractFeatureBuilder"
         // and its simplementations. Generally, each kind of "AbstractFeature" will have an implementation of the
         // "AbstractFeatureBuilder". 
         //The building of these features typically requires counting term frequencies in certain ways, which is 
         // a computationally extensive process. Two alternative sets of "AbstractFeatureBuilder" classes are 
         // implemented. The first set contains classes named as "FeatureBuilderXYZMultiThread", which splits the corpus
         // into segments and run several threads are run in parallel then aggregate results; the second set contain
         // classes named as "FeatureBuilderXYZ" which are the single threaded options. 
         //Also note that counting in JATE is case-sensitive: JATE creats a one-to-many mapping from canonical term forms to
         // variants found in a corpus. When counting, each variant is searched in the corpus, and the frequency adds up to a sum
         // as the total frequency for the canonical form. Scoring and ranking are based on the canonical forms also. 

         /* Option 1: Due to use of multi-threading, this can significantly occupy your CPU and memory resources. It is
            better to use this way on dedicated server machines, and only for very large corpus.

            NOTE: YOU DO NOT need all the following features for every single algorithm. This class creates all for the purpose
            of showcase all algorithms.
          */
         /*
         FeatureCorpusTermFrequency wordFreq =
            new FeatureBuilderCorpusTermFrequencyMultiThread(wordcounter, lemmatizer).build(wordDocIndex);
         FeatureDocumentTermFrequency termDocFreq =
            new FeatureBuilderDocumentTermFrequencyMultiThread(wordcounter, lemmatizer).build(termDocIndex);
         FeatureTermNest termNest =
            new FeatureBuilderTermNestMultiThread().build(termDocIndex);
         FeatureRefCorpusTermFrequency bncRef =
            new FeatureBuilderRefCorpusTermFrequency(args[1]).build(null);
         FeatureCorpusTermFrequency termCorpusFreq =
            new FeatureBuilderCorpusTermFrequencyMultiThread(wordcounter, lemmatizer).build(termDocIndex);
         */
 
         /* Option #2 */                
         //If you use single-threaded feature builders, you need to create ONE instance of TermFreqCounter.
         //Before with multi-threaded feature builders, one instance of TermFreqCounter is created for each 
         //thread.
         TermFreqCounter npcounter = new TermFreqCounter();
         FeatureCorpusTermFrequency wordFreq =
                new FeatureBuilderCorpusTermFrequency(npcounter, wordcounter, lemmatizer).build(wordDocIndex);
         FeatureDocumentTermFrequency termDocFreq =
                new FeatureBuilderDocumentTermFrequency(npcounter, wordcounter, lemmatizer).build(termDocIndex);
         FeatureTermNest termNest =
                new FeatureBuilderTermNest().build(termDocIndex);
         FeatureRefCorpusTermFrequency bncRef =
                new FeatureBuilderRefCorpusTermFrequency(reference_corpus_path).build(null);
         FeatureCorpusTermFrequency termCorpusFreq =
                new FeatureBuilderCorpusTermFrequency(npcounter, wordcounter, lemmatizer).build(termDocIndex);
         


         //##########################################################
         //#         Step 3. For each algorithm you want to test    #
         //#         create an instance of the algorithm class,     #
         //#         and also an instance of its feature wrapper.   #
         //##########################################################


         AlgorithmTester tester = new AlgorithmTester();

         //NOTE that each algorithm will need its own "FeatureWrapper" (in uk.ac.shef.dcs.oak.jate.core.algorithm)
         //Each feature wrapper may require different kinds of "AbstractFeature". You can find details of these in
         //the javadoc. The purpose of "FeatureWrapper" is to encapsulate the underlying feature (stores) and provide
         //access to only the features required by the corresponding algorithm

         tester.registerAlgorithm(new TFIDFAlgorithm(), new TFIDFFeatureWrapper(termCorpusFreq));
         tester.registerAlgorithm(new GlossExAlgorithm(), new GlossExFeatureWrapper(termCorpusFreq, wordFreq, bncRef));
         tester.registerAlgorithm(new WeirdnessAlgorithm(), new WeirdnessFeatureWrapper(wordFreq, termCorpusFreq, bncRef));
         tester.registerAlgorithm(new CValueAlgorithm(), new CValueFeatureWrapper(termCorpusFreq, termNest));
         tester.registerAlgorithm(new TermExAlgorithm(), new TermExFeatureWrapper(termDocFreq, wordFreq, bncRef));
         tester.registerAlgorithm(new RIDFAlgorithm(), new RIDFFeatureWrapper(termCorpusFreq));
         tester.registerAlgorithm(new AverageCorpusTFAlgorithm(), new AverageCorpusTFFeatureWrapper(termCorpusFreq));
         tester.registerAlgorithm(new FrequencyAlgorithm(), new FrequencyFeatureWrapper(termCorpusFreq));

         tester.execute(termDocIndex, output_path);
         System.out.println(new Date());

      }
      catch (Exception e) {
         e.printStackTrace();
      }
   }
}