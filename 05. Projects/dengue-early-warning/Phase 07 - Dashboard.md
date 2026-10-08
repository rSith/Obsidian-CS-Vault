---
type: project-note
project: Dengue Early-Warning
status: stub
tags: [project, ml, web]
---
# Phase 07 — Dashboard
> [!info] [[Dengue Early-Warning - Project Home]] · Roadmap weeks 19–20, target finish 2027-02-28
> **Study notes:** [[07.00 Dashboard]] · **Revision:** [[07.99 Summary - Dashboard]]

> [!abstract] Phase goal
> A simple web page a non-technical health officer could understand in 30 seconds: which districts are at risk, and how confident the system is.

## Steps
- [ ] Sketch the layout on paper: map on the left, detail panel on the right, "last updated" in the header
- [ ] Serve the page from a Flask template → [[07.06 Flask Templates with Jinja2]]
- [ ] Lay out the page so it works on a phone → [[07.01 CSS Layout with Flexbox and Grid]]
- [ ] Fetch `/api/alerts/latest` and log it to the console → [[07.02 JavaScript fetch and Promises]]
- [ ] Map: district boundaries coloured by alert level, with predicted cases in a tooltip → [[07.04 Leaflet and GeoJSON]]
- [ ] Detail chart: actual against predicted, with the uncertainty band and threshold lines → [[07.05 Chart.js]]
- [ ] Alert table that responds to clicks → [[07.03 DOM Manipulation and Events]]
- [ ] Add "How to read this", the data sources, and the note that this is decision support
- [ ] Check colour-blind readability, keyboard use and contrast; test with one non-technical person → [[07.07 Dashboard UX and Accessibility]]

## Done when
- [ ] The map shows every district coloured by alert level
- [ ] Clicking a district shows its chart with thresholds
- [ ] "How to read this" and the data-source notes are visible
- [ ] The page works on a phone screen
- [ ] One non-technical person has tested it and their feedback is addressed

---
## Work log
Step sections are added here as each step is finished.
