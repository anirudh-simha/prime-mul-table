(ns prime-numbers-table.core-test
  (:require [clojure.test :refer :all]
            [prime-numbers-table.core :refer :all]))

;(deftest a-test
;  (testing "FIXME, I fail."
;    (is (= 0 1))))

(deftest test-is-prime
  (is (true? (is-prime? 2)))
  (is (false? (is-prime? 4)))
  (is (false? (is-prime? 1)))

)

(deftest test-get-prime-numbers
  (is (= [2 3 5] (get-prime-numbers 3)))
  (is (= [] (get-prime-numbers 0)))
  (is (= [2] (get-prime-numbers 1)))
)

(deftest test-parse-int
  (is (nil? (parse-int "ac")))
  (is (= 2 (parse-int "2")))
)

(deftest test-print-mul-tables
  (is (= (with-out-str (print-mul-tables [2 3 5])) "*\t|2.0\t|3.0\t|5.0\n2.0\t|4.0\t|6.0\t|10.0\n3.0\t|6.0\t|9.0\t|15.0\n5.0\t|10.0\t|15.0\t|25.0\n"))
)

(deftest test-get-mul-matrix
  (is (= [[1.0 2.0 3.0 5.0] [2.0 4.0 6.0 10.0] [3.0 6.0 9.0 15.0] [5.0 10.0 15.0 25.0]] (get-mul-matrix [2 3 5])))
)
