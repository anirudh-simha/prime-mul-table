(ns prime-numbers-table.core
  (:gen-class)
  (:require [clojure.core.async :as async])
  (:require [clojure.core.matrix :as matrix]))

(defn is-prime?
  [number]
  (if (= 2 number) true
    (if (< number 2) false
      (let [range-end (+ 1 (Math/sqrt number))
            start-num 2]
        (loop [i start-num]
          (if (<= i range-end)
            (if (zero? (mod number i))
              false
              (recur (inc i))
              )
            true))))))

(defn get-mul-matrix
  [list-of-primes]
  (let [prime-matrix [(into [] (concat [1] list-of-primes))]
        transposed-prime-matrix (matrix/transpose prime-matrix)
        mul-table (matrix/mmul transposed-prime-matrix prime-matrix)]
    mul-table
  )
)

(defn print-mul-tables [list-of-primes]
        (let [mul-table   (map #(clojure.string/join "\t|" %) (assoc-in (get-mul-matrix list-of-primes) [0 0] "*"))]

        (doseq [row  mul-table]
          (println (str row))
        )

    ))

(defn get-prime-numbers
  [input-range]
    (let [channel (async/go-loop [i 2
           counter 0
           list-of-primes (transient [])]
        (if (= counter input-range)
            (persistent! list-of-primes)
              (if (true? (is-prime? i))
                (recur (inc i) (inc counter) (conj! list-of-primes i))
                (recur (inc i) counter list-of-primes)
              )
        )
    )]
      (async/<!! channel))
    
) 

(defn parse-int [number-string]
  (try (Integer/parseInt number-string)
(catch Exception e nil)))

(defn -main
  [& args]
  (if (not (=(count args) 1))(println "Please provide an integer as range upto which to generate prime number multiplication tables")
    (if (nil? (parse-int (nth args 0)))
      (println "please provide a number")
      (print-mul-tables (get-prime-numbers (Integer. (nth args 0))))
    )
  )
)


