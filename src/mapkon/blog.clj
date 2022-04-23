(ns mapkon.blog
  (:require [clojure.java.io :as io]))

(def entries-folder-name (str (-> (io/file ".")) "/docs/entries/"))

(defn trace [value]
  (println value)
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

(def index-html "./docs/index.html")

(defn process-blogs [blogs]
  (let [html-content (slurp index-html)
        blog-content (map #(markdown->html (slurp %)) blogs)]
    (println (clojure.string/replace html-content #"[{content}]" (apply str blog-content)))))

;; (defn process-blogs [blogs]
;;  (let [content (map #(markdown->html (slurp %)) blogs)
;;    (with-open [wrtr (io/writer index-html)]
;;      (let [current-content (slurp index-html)
;;            blog-content (apply str content)])
;;      (println content current-content blog-content))]))

(with-open [rdr (io/reader index-html)]
  (doseq [line (line-seq rdr)]
    (println line)))

(process-blogs (get-blogs))
