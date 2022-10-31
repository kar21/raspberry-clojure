(ns water.display
  (:require
   [water.state :refer [state]]))

; global state

(defn global-state []
  (str
   (if (:enabled @state)
     "running"
     "stopped")
   " minute: " (get-in @state [:current :minute])))

; valve intensity levels

(def eight-fractions
  {0 " " 
   1 "▁" ; LOWER ONE EIGHTH BLOCK 1/8
   2 "▂" ; LOWER ONE QUARTER BLOCK 2/8
   3 "▃" ; LOWER THREE EIGHTHS BLOCK 3/8
   4 "▄" ; LOWER HALF BLOCK 4/8
   5 "▅" ; LOWER FIVE EIGHTHS BLOCK 5/8
   6 "▆" ; LOWER THREE QUARTERS BLOCK 6/8
   7 "▇" ; LOWER SEVEN EIGHTHS BLOCK 7/8
   8 "▇" ; FULL BLOCK 8/8
   }) 

(defn two-letter-eight-fraction [i]
  (str (get eight-fractions (* 2 i)) " ")
  )


(defn valves-intensity []
  (->>
    (map two-letter-eight-fraction (:valves @state)) 
    (into [])
   (apply str)))

; current valve status

(def valve-status 
  {true "🚿" ; https://unicode-table.com/en/1F6BF/
   false "🔒"  ; https://unicode-table.com/en/1F512/
   }
  )

(defn current-valves-status []
  (->>
   (map #(get valve-status %) (get-in @state [:current :open] ))
   (into [])
   (apply str)
   ))

(comment 
    (current-valves-status)
   (count (current-valves-status))
  
  ;
  )


(defn print-state []
  (let [global (global-state)
        intensity (valves-intensity)
        open (current-valves-status)
        display-text (str global "\r\n " intensity "\r\n" open "\r\n")
        ]
    (println display-text)
    )
  )

(comment
  (global-state)
  (valves-intensity)
  (current-valves-status)
  (print-state)



;  
  )



