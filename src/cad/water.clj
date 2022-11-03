(ns cad.water
  (:require
   [scad-clj.model :as m]
   [cad.helper :refer [render]]))

; colors

(def brown [2/3 1/2 2/5 1])
(def green [1/3 2/2 2/5 1])



; variables

(def space-between 50)

(def r-inner 20)
(def r-outer 25)
(def h-plate 5)

(def nr-cols 8)
(def nr-rows 2)

; bottom plate

(def bottom-plate
  ;(m/translate [-150 -200 0]
  (m/cube 300 500 h-plate)
  ; )
  )


(def walls
  (m/union
   (m/cube 300 10 100)
   (m/translate [0 300 0] (m/cube 300 10 100))
   (m/cube 10 500 100)
   (m/translate [300 0 0] (m/cube 10 500 100))))

(comment
  bottom-plate
  (render bottom-plate)
  (render walls)
  ;
  )

; holes

(defn move-to-row-col [r c item]
  (m/translate [(* r space-between)
                (* c space-between)
                0]
               item))

(defn hole 
  "creates a hole in the bottom 
   params: r=row c=col, both numbers"
  [r c]
  (->> (m/cylinder r-inner h-plate)
       (move-to-row-col r c)
       (m/color brown)))

(comment
  (render
   (m/union
    (hole 0 0)
    (hole 0 1)
    (hole 1 0)
    (hole 2 0)
    (hole 3 0)
    (hole 3 1)
    ;(hole 10 10)
    ))
  ;
  )

(def holes
  (apply m/union
         (for [r (range nr-rows)
               c (range nr-cols)]
           (hole r c))))

(comment
  (render holes)

  (render
   (m/difference
    (m/cube 400 400 h-plate)
    (hole 0 0)
    (hole 0 3)))


  ;
  )

(def botton-plate-with-holes
  (m/difference
   bottom-plate
   holes))


(render botton-plate-with-holes)

; pipes

(comment
  (render
   (m/cylinder 10 20))

  (render
   (->> (m/cylinder 5 10 :center [0 0 0])
        (m/color green)
        (m/with-fn 16)))

  ;
  )


(defn make-cylinder [d c]
  (->>  (m/cylinder d 20 :center [0 0 0])
        (m/color c)
        (m/with-fn 48)))

(comment
  (render (make-cylinder 10 green))
  (render (make-cylinder 6 brown))

  (render
   (m/union
    (m/cube 60 60 1)
    (m/difference
     (make-cylinder 10 green)
     (make-cylinder 6 brown))))
  ;
  )

(defn pipe [r c]
  (->> (m/difference
        (make-cylinder r-outer green)
        (make-cylinder r-inner brown))
       (move-to-row-col r c)
       (m/color green)))

(comment
  (render (pipe 0 0))

  (render
   (m/union
    (pipe 0 0)
    (pipe 1 1)
    (pipe 3 1)))

  ;
  )

(def pipes
  (apply m/union
         (for [r (range nr-rows)
               c (range nr-cols)]
           (pipe r c))))

(comment
  (render pipes)
  ;
  )

(def complete-model
  (m/union
   botton-plate-with-holes
   pipes))


(comment
  (render complete-model)

  ;
  )