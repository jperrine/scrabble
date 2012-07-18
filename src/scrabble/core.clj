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
      (println word-two)))

(defn search-for-word [word]
  (map #(possible-word? (normalize word) %) words))

(defn -main [& args]
  (let [[options _ _] (cli args ["-w" "--word" "word to use"])
        word (:word options)]
    (search-for-word word)))