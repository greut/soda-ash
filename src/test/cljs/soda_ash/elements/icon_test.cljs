(ns soda-ash.elements.icon-test
  (:require [cljs.test :refer-macros [deftest testing is use-fixtures]]
            [cljs-react-test.utils :as tu]
            [dommy.core :as dommy :refer-macros [sel1]]
            [reagent.core :as reagent]
            [soda-ash.element :as s]
            [soda-ash.elements.test-helpers :as h]))


;; Util

(def ^:dynamic c)

(use-fixtures :each (fn [test-fn]
                      (binding [c (tu/new-container!)]
                        (test-fn)
                        (tu/unmount! c))))



;; Tests


(deftest no-soda
  (let [_ (reagent/render [s/icon {}] c)
        node (sel1 c [:i])
        class (h/get-class node)]
    (is (= "icon" class))))


(deftest with-soda
  (let [_ (reagent/render [s/icon {:soda {:bordered? true
                                          :circular? true
                                          :fitted? true
                                          :inverted? true
                                          :link? true

                                          :flipped :horizontally
                                          :rotated :clockwise

                                          :color :teal
                                          :icon :user
                                          :size :tiny
                                          :state :loading}}] c)
        node (sel1 c [:i])
        class (h/get-class node)]
    (doto class
      (h/check-class #"bordered")
      (h/check-class #"circular")
      (h/check-class #"fitted")
      (h/check-class #"inverted")
      (h/check-class #"link")

      (h/check-class #"horizontally flipped")
      (h/check-class #"clockwise rotated")

      (h/check-class #"teal")
      (h/check-class #"user")
      (h/check-class #"tiny")
      (h/check-class #"loading"))))
