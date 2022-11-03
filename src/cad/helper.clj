(ns cad.helper
  (:require 
   [scad-clj.model :as m]
   [scad-clj.scad :refer [write-scad]]))
  
  (defn render [what]
    (spit "render.scad" (write-scad what)))
  
; https://github.com/PEZ/scad-clj-workflow/blob/master/src/scad_clj_workflow/hydroponics/extender.clj