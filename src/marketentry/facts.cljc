(ns marketentry.facts
  "Czech Republic (CZE) market-entry catalog.

  Scoped to CZE only. Prior revisions of this catalog carried unlabeled
  USA/DEU/POL entries byte-identical to boilerplate entries in unrelated
  sibling repos -- no README/docs in this repo ever documented a
  deliberate comparative-jurisdiction design, so this was scaffold-copy
  contamination, not intent. Removed 2026-07-23; extend `catalog` with a
  new jurisdiction only behind its own primary-source citation, never by
  re-copying another repo's entry verbatim.")
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
          :corporate-number-provenance "https://or.justice.cz/"}})

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
