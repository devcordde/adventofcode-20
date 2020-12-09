(ns solution
  (:require [clojure.set :as sets]))

(def child-bag #"(\d+) (\w+ \w+) bags?(?:, )?")

(def bag-format #"(?m)^(\w+ \w+) bags contain (?:(?:no other bags)|(.+))\.$")

(defn parse-int [s]
  (Integer/parseInt s))

(defn parse-children [[_ children-str :as v]]
  (assoc
    v 1
    (if children-str
      (->> (re-seq child-bag children-str)
           (map (comp vec rseq #(update % 0 parse-int) #(subvec % 1)))
           (into {}))
      {})))

(defn parse-bag-rules [input]
  (->> (re-seq bag-format input)
       (map #(subvec % 1))
       (map parse-children)
       (into {})))

(defn create-parent-map [bag-rule-map]
  (reduce-kv (fn [m k v] (reduce #(update %1 %2 conj k) m (keys v)))
             (zipmap (keys bag-rule-map) (repeat #{}))
             bag-rule-map))

(defn parent-bags [bag-map bag-name]
  (let [parents (bag-map bag-name)]
    (if (seq parents)
      (reduce sets/union parents (map (partial parent-bags bag-map) parents))
      #{})))

(defn contained-bag-count [bag-map bag-name]
  (if-let [children (seq (bag-map bag-name))]
    (reduce + 1 (map (fn [[name count]] (* count (contained-bag-count bag-map name))) children))
    1))

(defn solve [input]
  (let [child-map (parse-bag-rules input)
        parent-map (create-parent-map child-map)]
    (println "There are" (count (parent-bags parent-map "shiny gold")) "bags that contain a shiny gold bag.")
    (println "There are" (dec (contained-bag-count child-map "shiny gold")) "bags within a single shiny gold bag.")))

(solve (slurp (first *command-line-args*)))
