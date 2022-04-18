(ns mapkon.blog
  (:require [clojure.java.io :as io]))

(def entries-folder-name (str (-> (io/file ".")) "/docs/entries/"))

(defn get-blogs []
  (->>
   entries-folder-name
   clojure.java.io/file
   file-seq  (filter #(.isFile %)) (map #(.getPath %))))

(defn process-blogs [blogs]
  (map process-blog blogs))

(defn process-blog [blog]
  (str blog))

(process-blogs get-blogs)
