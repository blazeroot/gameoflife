(ns gameoflife.ui
  (:require [clojure.core.matrix :as m]
            [seesaw.core :as saw]))

(defn clear
  "Clears the screen, using ANSI control characters."
  []
  (let [esc (char 27)]
    (print (str esc "[2J"))
    (print (str esc "[;H"))))

(defn print-world
  "Nicely pritns given matrix"
  [matrix]
  (m/pm matrix))
