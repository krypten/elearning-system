<?php
 
// This is the code which calls Java program. Here we have a static array,
// but you can of course fill it dynamically and pass it to Java then..
 

$time_start = microtime(true);
require_once("Java.inc");

    function debug($key, $value) {
        echo '<br/> '. $key . " : " . $value .'<br/>';
    }

    function getSystem($key) {
        $mc = new Memcached(); 
        $mc->addServer("localhost", 11211); 
        $system = $mc->get($key);
        // var_dump($system);
        return $system;
    }

    function setSystem($key, $system) {
        $mc = new Memcached(); 
        $mc->addServer("localhost", 11211); 
        $mc->set($key, $system);
    }

    function createBridge() {
        $demo = new java("main.bridge");
        echo 'New Bridge instance created';
        $demo->system->print();
        return $demo->system;
    }

    function startModule($system, $numModules) {
        $system->run();
        $system->engine->initModule($numModules);
    }

    function getModules($engine, $domain, $size) {
        $modules = array();
        $mods = $engine->learningMash->getDomainModules($domain);
        for ($i = 0; $i < $size; $i++) {
            array_push($modules, $mods->get($i));
        }
        return $modules;
    }

    function getState($engine, $observation, $size) {
        $state = $engine->addObservation($observation);
        $engine->getObsStates();
        return $state;
    }

    function showLearningObject($engine, $moduleName, $state) {
        $lo = $engine->learningMash->getLOFromModule($moduleName, $state);
        $engine->learningMash->removeLOFromModule($moduleName, $state);
        return $lo->general->getTitle();
    }

    function showLOFromModule($engine, $observations, $modules, $size) {
        for ($i = 0; $i < $size; $i++) {
            $state = getState($engine, $observations[$i], $size);
            echo $observations[$i] . ' -> ' . showLearningObject($engine, $modules[$i], $state) . '<br/>' ;
        }
    }

    function getLO($engine, $domain, $observations) {
        $modules = getModules($engine, $domain);
        showLOFromModule($engine, $observations, $modules, count($modules));
    }

    function printArrayList($arr, $size) {
        $size = $arr->size();
        echo '[';
        for($i = 0; $i < 7; $i++) {
	        echo $arr->get($i) . ' ';
        }
        echo '] <br />';
    }

    function testJavaBridge() {    
        $domain = 'DBMS';
        $observations = array(0, 0, 2, 2, 2, 2, 2, 2);
        $system = createBridge();
        startModule($system, count($observations));
        getLO($system->engine, $domain, $observations);
        $system->engine->clearObservations();
    }

?>
