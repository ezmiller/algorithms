(ns clojure-mergesort.core
  (:use clojure.test))

(defn recurse-merge [l r]
  (cond
    (= 0 (count l)) r
    (= 0 (count r)) l
    :else
      (if (> (l 0) (r 0))
        (vec (concat [(r 0)] (recurse-merge (vec (drop 1 r)) l)))
        (vec (concat [(l 0)] (recurse-merge (vec (drop 1 l)) r))))))

(defn my-merge [l r]
  (def l-idx 0)
  (def r-idx 0)
	(def l-length (count l))
	(def r-length (count r))
  (def merged [])
  (while (and (< l-idx l-length) (< r-idx r-length))
    (if (> (l l-idx) (r r-idx))
      (do
        (def merged (concat merged (vector (r r-idx))))
        (def r-idx (inc r-idx)))
      (do
        (def merged (concat merged (vector (l l-idx))))
        (def l-idx (inc l-idx)))))
  (while (< l-idx l-length)
    (do
      (def merged (concat merged (vector (l l-idx))))
      (def l-idx (inc l-idx))))
  (while (< r-idx r-length)
    (do
      (def merged (concat merged (vector (r r-idx))))
      (def r-idx (inc r-idx))))
  (apply vector merged))

(defn merge-sort [x]
  (cond
    (= 1 (count x)) x
    :else
      (do
        (recurse-merge
          (merge-sort (vec (first (partition (/ (count x) 2) x))))
          (merge-sort (vec (second (partition (/ (count x) 2) x))))))))

(deftest test-merge
	(is (= [3 4 8 9] (my-merge [3 9] [4 8])))
	(is (= [3 4 8 9 10 13] (my-merge [3 9 10 13] [4 8]))))

(deftest test-recurse-merge
  (is (= [3 4] (recurse-merge [3] [4])))
  (is (= [3 4 8 9] (recurse-merge [3 9] [4 8])))
  (is (= [3 4 8 9 10 13] (recurse-merge [3 9 10 13] [4 8])))
  (is (= [1 2 3 4] (recurse-merge [3 4] [1 2]))))

(deftest test-merge-sort
  (is (= [1 2] (merge-sort [2 1])))
  (is (= [1 2 3 4] (merge-sort [4 3 2 1]))))

(defn -main []
  (run-all-tests #"clojure-mergesort.core")
)
