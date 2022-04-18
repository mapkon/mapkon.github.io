(ns mapkon.blog-test
  (:require [clojure.test :refer :all]
            [mapkon.blog :refer :all]))

(def blogs (get-blogs))

(deftest get-blogs-validity
  (testing "get-blogs does not return nil"
    (is (not (= nil blogs)))))

(deftest process-blogs-validity
  (testing "process-blogs does not return nil"
    (is (not (= nil (process-blogs blogs))))
    (is (empty? (process-blogs [])))))

(deftest markdown->html-tests
  (testing "Transformation of markdown directives into html title tags"
    (is (= "<h1>This is a h1 title</h1>" (markdown->html "# This is a h1 title")))
    (is (= "<h2>This is a h2 title</h2>" (markdown->html "## This is a h2 title")))
    (is (= "<h3>This is a h1 title</h3>" (markdown->html "### This is a h1 title")))))

(run-tests 'mapkon.blog-test)
