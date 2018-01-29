(ns otp
 (:require [clj-otp.core :refer [totp]]
           [clj-otp.base32 :refer [decode-data]]))

(defn otp
  [s]
  (totp (decode-data s)))

(defn read-secret
  ([]
   (if-let [console (System/console)]
     (read-secret console)
     (read-line)))
  ([console]
   (if-let [secret (.readPassword console "Enter secret: " (object-array 0))]
     (String. secret)
     "")))

(defn err
  [s]
  (binding [*out* *err*]
    (println s)))

(defn -main [& [s]]
  (try
    (println (otp (or s (read-secret))))
    (catch IllegalArgumentException e
      (err "Secret key should be a base-32 string (A-Z, 2-7)")
      (System/exit 1))))
