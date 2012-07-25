(ns scrabble.core
  (:use clojure.tools.cli))

(def words (line-seq (clojure.java.io/reader (clojure.java.io/file "/usr/share/dict/words"))))

(defn lower-case [word]
  (.toLowerCase word))

(defn normalize [word]
  (apply str (sort (lower-case word))))

(defn substring? [substring s]
  (.contains s substring))

(defn possible-word? [letters word-two]
  (if (substring? (normalize word-two) letters)
    word-two))

(defn search-for-word [word]
  (filter #(> (count %) 1) (map #(possible-word? (normalize word) %) words)))

(defn -main [& args]
  (let [[options _ _] (cli args ["-w" "--word" "word to use"])
        word (:word options)]
    (println (search-for-word word))))