(ns mapkon.blog
  (:require [clojure.java.io :as io]))

(def entries-folder-name (str (-> (io/file ".")) "/docs/entries/"))

(defn trace [value]
  (print value)
  value)

(defn get-blogs []
  (->>
   entries-folder-name
   clojure.java.io/file
   file-seq (filter #(.isFile %)) (map #(.getPath %))))

(defn markdown->html [markdown-line]
  (clojure.string/replace markdown-line
                          #"(#{1,6})\s+([\w ]*)"
                          (fn [[result group1 group2]]
                            (let [tag (str "h" (count group1) ">")]
                              (str "<" tag group2 "</" tag)))))

(defn process-blogs [blogs]
  (map #(markdown->html (slurp %)) blogs))
