(ns cad.demo
  (:require
   [scad-clj.model :as m]
   [cad.helper :refer [render] :as confusion]
   ))

; this 3 do exactly the same thing
; (render x)
; (confusion/render x)
; (cad.helper/render x)


;; Small plug for a 21mm watering pipe (plants)

(render
 (let [top (->> (m/cylinder (/ 21.5 2) 0.1)
                (m/translate [0 0 -20])
                (m/with-fn 48))
       bottom (->> (m/cylinder (/ 15 2) 0.1)
                   (m/with-fn 48))]

   (m/hull top bottom)))

;; Experiments for mounting brackets using hull

(defn holes [rs height]
  (let [points [[10 10]
                [-10 10]
                [-10 -10]
                [10 -10]]]
    (map #(->> (m/cylinder rs height)
               (m/translate %)
               (m/with-fn 32)) points)))

(render (m/union
         (m/difference (apply m/union (holes 1 2))
                       (apply m/union (holes 0.5 3)))
         (m/difference (apply m/hull (holes 5 4))
                       (->> (holes 4 4)
                            (apply m/hull)
                            (m/translate [0 0 1])))))

;; monitor stand

(def mid-block (->> (m/cube 6 6 1)
                    (m/translate [0 0 15])))

(render mid-block)

(render (m/cube 5 5 1))

(render (m/union
         (m/cube 5 5 1)
         (m/translate [15 0 15]
                      (m/cube 5 5 1))))

(render (m/hull
         (m/cube 5 5 1)
         (m/translate [15 0 15]
                      (m/cube 5 5 1))))



(defn leg [v]
  (->> (m/cube 5 5 1)
       (m/translate v)
       (m/hull mid-block)))

(render (m/union (leg [15 10 0])
                 (leg [15 -10 0])
                 (leg [-15 10 0])
                 (leg [-15 -10 0])))

; hydroponic

(def opening-diameter 55)
(def outside-diameter 60)
(def curve-radius 10)
(def extender-height 20)

(defn round-shape [diameter]
  (let [circle (m/circle curve-radius)
        circles (for [x [-1 1]
                      y [-1 1]]
                  (as-> [x y 0] $
                    (map (partial * (- (/ diameter 2) curve-radius)) $)
                    (m/translate $ circle)))]
    (apply m/hull circles)))

(render
 (round-shape 10))

(render
 (m/difference (round-shape outside-diameter)
               (round-shape opening-diameter)))


(render (->> (m/difference (round-shape outside-diameter)
                           (round-shape opening-diameter))
             (m/extrude-linear {:height extender-height
                                :center true
                                :scale 0.9})))


