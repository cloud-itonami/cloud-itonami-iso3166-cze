(ns marketentry.facts "Czech Republic market-entry catalog.")
(def catalog
  {"CZE" {:name "Czech Republic"
          :owner-authority "MMR / NEN (Národní elektronický nástroj)"
          :legal-basis "Zákon o zadávání veřejných zakázek; EU directives"
          :national-spec "NEN / VVZ + IČO"
          :provenance "https://nen.nipez.cz/"
          :required-evidence ["IČO record" "NEN registration record" "OR extract" "Authorized-representative record"]
          :rep-owner-authority "contracting authorities / MMR"
          :rep-legal-basis "EU establishment or Czech IČO entity for many procedures"
          :rep-provenance "https://nen.nipez.cz/"
          :corporate-number-owner-authority "ČSÚ / Justice OR"
          :corporate-number-legal-basis "IČO"
          :corporate-number-provenance "https://or.justice.cz/"}
   "USA" {:name "United States" :owner-authority "GSA/SAM.gov" :legal-basis "FAR" :national-spec "SAM.gov" :provenance "https://sam.gov/"
          :required-evidence ["EIN record" "SAM.gov registration record" "State business registration record" "SAM UEI verification record"]}
   "DEU" {:name "Germany" :owner-authority "e-Vergabe" :legal-basis "GWB/VgV" :national-spec "e-Vergabe" :provenance "https://www.evergabe-online.de/"
          :required-evidence ["Handelsregister extract" "e-Vergabe registration record" "USt-IdNr record" "Authorized-representative record"]}
   "POL" {:name "Poland" :owner-authority "e-Zamówienia" :legal-basis "PZP" :national-spec "e-Zamówienia" :provenance "https://ezamowienia.gov.pl/"
          :required-evidence ["NIP/KRS record" "e-Zamówienia registration" "CEIDG/KRS extract" "Authorized-representative record"]}})

(defn spec-basis [iso3] (get catalog iso3))
(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s) missing (remove catalog iso3s)]
     {:requested (count iso3s) :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note "R0 catalog seed"})))
(defn required-evidence-satisfied? [iso3 submitted]
  (when-let [{:keys [required-evidence]} (spec-basis iso3)]
    (= (count required-evidence) (count (filter (set submitted) required-evidence)))))
(defn evidence-checklist [iso3] (:required-evidence (spec-basis iso3) []))
(defn rep-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:rep-owner-authority sb)
      (select-keys sb [:rep-owner-authority :rep-legal-basis :rep-provenance]))))
(defn corporate-number-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:corporate-number-owner-authority sb)
      (select-keys sb [:corporate-number-owner-authority :corporate-number-legal-basis :corporate-number-provenance]))))
