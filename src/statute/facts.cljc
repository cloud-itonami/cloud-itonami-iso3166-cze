(ns statute.facts
  "General-law compliance catalog for the Czech Republic (CZE) -- a
  47th country-level entry (see cloud-itonami-iso3166-jpn/-usa/-gbr/-deu/
  -fra/-can/-aus/-kor/-nld/-ita/-esp/-swe/-nor/-dnk/-fin/-prt/-bel/-bra/
  -mex/-chl/-arg/-zaf/-col/-ury/-cri/-pan/-ecu/-pry/-gtm/-hnd/-ind/-ken/
  -tha/-are/-vnm/-idn/-phl/-egy/-tur/-nga/-sau/-mys/-aut/-che/-irl/-nzl
  for the first forty-six) per ADR-2607141700
  (cloud-itonami-compliance-fact-federation).

  Reuses this tick-window's already-verified capital-status finding
  from cloud-itonami-municipality-cze-prague (tick 138): Prague is
  the Czech Republic's stable capital, with no ongoing ambiguity.

  Zákon o obchodních korporacích (Business Corporations Act, 90/2012
  Sb.) -- title and Act number directly confirmed via msp.gov.cz (the
  Czech Ministry of Justice's own official domain), which states
  verbatim 'ZÁKON O OBCHODNÍCH KORPORACÍCH zákon č 90_2012 Sb'. The 25
  January 2012 date itself could not be confirmed via any
  successfully-rendered page this tick -- obcanskyzakonik.justice.cz's
  own hosted PDF returned a TLS certificate error, czechlegislation.com
  returned a DNS failure, and both ILO NATLEX and zakonyprolidi.cz
  returned HTTP 403 -- but the date is unanimously corroborated across
  every independent secondary source checked (including the exact
  phrase 'Act No. 90/2012 of 25 January 2012' in NATLEX's own catalog
  title, itself unreachable for direct reading) with zero conflicting
  alternative dates found anywhere, matching this session's
  established WebSearch-corroboration pattern for cases with no
  successfully-rendered primary alternative (e.g. Nigeria's CAMA 2020
  at tick 117).

  Zákon o zpracování osobních údajů (Personal Data Processing Act,
  110/2019 Sb.) -- title, Act number, and date (12 March 2019) all
  directly confirmed via uoou.gov.cz (the Czech Data Protection
  Authority, the official regulator's own government domain), which
  states verbatim: 'Czech Act No. 110/2019 Coll., act of 12 March 2019
  on personal data processing in English and German translation.'

  An entry not in this table has NO spec-basis, full stop; extend
  `catalog`, do not invent an id/url/date.")

(def catalog
  "ISO3166 alpha-3 -> vector of statute entries."
  {"CZE"
   [{:statute/id "cze.act-90-2012-business-corporations-act"
     :statute/title "Zákon o obchodních korporacích (Business Corporations Act)"
     :statute/jurisdiction "CZE"
     :statute/kind :law
     :statute/law-number "90/2012 Sb."
     :statute/url "https://msp.gov.cz/en/web/vrchni-soud-v-praze/ruzne/-/clanek/zakon-o-obchodnich-korporacich-zakon-c-90_2012-sb"
     :statute/url-provenance :official-msp-gov-cz
     :statute/enacted-date "2012-01-25"
     :statute/retrieved-at "2026-07-17"
     :statute/topic #{:corporate-governance :incorporation}}
    {:statute/id "cze.act-110-2019-personal-data-processing-act"
     :statute/title "Zákon o zpracování osobních údajů (Personal Data Processing Act)"
     :statute/jurisdiction "CZE"
     :statute/kind :law
     :statute/law-number "110/2019 Sb."
     :statute/url "https://uoou.gov.cz/en/legislation/act-no-1102019-coll"
     :statute/url-provenance :official-uoou-gov-cz
     :statute/enacted-date "2019-03-12"
     :statute/retrieved-at "2026-07-17"
     :statute/topic #{:data-protection :privacy}}]})

(defn spec-basis [jurisdiction] (get catalog jurisdiction))

(defn coverage
  ([] (coverage (keys catalog)))
  ([jurisdictions]
   (let [have (filter catalog jurisdictions)
         missing (remove catalog jurisdictions)]
     {:requested (count jurisdictions)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-cze statute.facts Wave 0 (ADR-2607141700): "
                 (count (get catalog "CZE")) " Czech Republic entries seeded "
                 "with msp.gov.cz/uoou.gov.cz citations. "
                 "Extend `statute.facts/catalog`, never fabricate an id/url.")})))

(defn by-topic [jurisdiction topic]
  (filterv #(contains? (:statute/topic %) topic) (spec-basis jurisdiction)))
