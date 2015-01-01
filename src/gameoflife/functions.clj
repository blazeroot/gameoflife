(ns gameoflife.functions
    (:require [clojure.core.matrix :as m]))

(defn generate-world
  "Takes size of the board and generates size x size board
   with random data"
  [size]
  (let [values [true false]]
    (m/matrix (repeatedly size #(map rand-nth (repeat size values))))))

(defn reset-world
  "Takes atom with world and repopulates it"
  [world-atom]
  (let [size (m/row-count @world-atom)]
    (reset! world-atom (generate-world size))))

(defn neighbours
  "Takes matrix and x, y (cell coordinates) and returns all neighbours of given element from 2d matrix."
  [matrix x y]
  (for [i (range (- x 1) (+ x 2))
        j (range (- y 1) (+ y 2))
        :let [max-i (m/row-count matrix)
              max-j (m/column-count matrix)]
        :when (and (>= i 0)
                   (>= j 0)
                   (<  i max-i)
                   (<  j max-j)
                   (not (and (== i x) (== j y))))]
      (m/mget matrix i j)))

(defn alive?
  "Takes matrix and x, y (cell coordinates) and checks if element should be alive in next step."
  [matrix x y]
  (let [cell (m/mget matrix x y)
        n (count (keep #(if (= true %) %) (neighbours matrix x y)))]
    (if (or (and cell
                 (some {2 3} [n]))
            (and (not cell)
                 (= n 3)))
      true false)))

(defn step
  "Takes matrix and returns next state"
  [matrix]
  (let [size-x (m/row-count matrix)
        size-y (m/column-count matrix)
        elements (for [x (range 0 size-x)
                       y (range 0 size-y)]
                   (alive? matrix x y))]
    (m/matrix (partition size-x elements))))
