# CS Vault

My Computer Science knowledge base, kept in [Obsidian](https://obsidian.md). It holds my university course notes, self-study notes, reusable concept notes and the working notes behind my projects: about 580 Markdown notes, all linked to each other.

> [!NOTE]
> This is an Obsidian vault, so the notes use `[[wikilinks]]`, callouts, embeds and `.base` views. GitHub shows them as plain text. For working links and dashboards, clone the repository and open it in Obsidian (see [Using this vault](#using-this-vault)).

## What's inside

| Folder | Contents |
|---|---|
| [`00. Home.md`](00.%20Home.md) | The dashboard: links to every course, project and hub, plus "still to finish" and "recently edited" views |
| [`02. University`](02.%20University) | Notes for my Level 3, Semester I courses: lessons, lecture notes, past papers and revision sheets |
| [`03. Self-Study`](03.%20Self-Study) | Notes from online courses, and a [Learning Tracker](03.%20Self-Study/00.%20Learning%20Tracker.md) of everything I'm studying outside university |
| [`04. Concepts`](04.%20Concepts) | One note per idea (machine learning, deep learning, LLMs and prompting, tools), written once and linked from everywhere else |
| [`05. Projects`](05.%20Projects) | A project home, phase or week progress notes, and the lessons each stage needs |
| [`06. Learning in Public`](06.%20Learning%20in%20Public) | Drafts of LinkedIn posts about what I'm learning |
| [`Templates`](Templates) | Note templates: Lecture Note, Concept Note, Course Home, Project Home, Weekly Review |
| [`01. Old_Vault`](01.%20Old_Vault) | The previous version of this vault, kept as an archive |

## University

| Course | Notes |
|---|---|
| **CMIS 3114** Data Communication & Computer Networks | [Course home](02.%20University/CMIS%203114%20-%20Data%20Communication%20%26%20Computer%20Networks/CMIS%203114%20Course%20Home.md) · [Full overview](02.%20University/CMIS%203114%20-%20Data%20Communication%20%26%20Computer%20Networks/00.%20CMIS%203114%20Course%20Overview.md). Lessons for all eight chapters (introduction through network security), past-paper questions from 2018/19 to 2023/24 mapped to topics, and exam revision sheets |
| **CMIS 3124** Web Designing & E-Commerce | [Course home](02.%20University/CMIS%203124%20-%20Web%20Designing%20%26%20E-Commerce/CMIS%203124%20Course%20Home.md) · [Full overview](02.%20University/CMIS%203124%20-%20Web%20Designing%20%26%20E-Commerce/00.%20Course%20Overview.md). Sixteen units from HTML and CSS foundations to ReactJS, Bootstrap, accessibility, e-commerce, web security, AI in web development and future trends, with past papers and revision notes |
| **CMIS 3134** Computer Architecture & Compiler Design | [Course home](02.%20University/CMIS%203134%20-%20Computer%20Architecture%20%26%20Compiler%20Design/CMIS%203134%20Course%20Home.md). Lecture notes on compilers, regular expressions, automata, NDFA to DFA conversion, Moore and Mealy machines, lexical and syntax analysis, and a past-paper topic map |
| **CMIS 3142** Computational Methods | [Course home](02.%20University/CMIS%203142%20-%20Computational%20Methods/CMIS%203142%20Course%20Home.md) (just started) |
| **CMIS 3153** Advanced Database Systems | [Course home](02.%20University/CMIS%203153%20-%20Advanced%20Database%20Systems/CMIS%203153%20Course%20Home.md) (just started) |

## Self-study

- **Machine Learning Specialization** (Andrew Ng, DeepLearning.AI)
- **Machine Learning in Production** (Andrew Ng)
- **Practical Prompt Engineering for Developers** and other Coding with AI courses
- **GitHub Foundations**

Progress for each course is ticked off in the [Learning Tracker](03.%20Self-Study/00.%20Learning%20Tracker.md).

## Projects

| Project | What it is | Stage | Code |
|---|---|---|---|
| [PlantFolio](05.%20Projects/plantfolio/PlantFolio%20-%20Project%20Home.md) | A web platform for plant lovers to profile their plants, track health, swap plants and diagnose leaf disease from a photo. PHP, MySQL and vanilla JavaScript, with a Flask and TensorFlow ML service | Building | [rSith/plantfolio](https://github.com/rSith/plantfolio) |
| [Dengue Early-Warning](05.%20Projects/dengue-early-warning/Dengue%20Early-Warning%20-%20Project%20Home.md) | Forecasts weekly dengue cases for each Sri Lankan district 4 to 8 weeks ahead from weather and case data, and raises Normal / Watch / Warning alerts on a map dashboard | Planning | [rSith/dengue-early-warning](https://github.com/rSith/dengue-early-warning) |
| [DocFinder v2](05.%20Projects/DocFinder%20v2/DocFinder%20v2%20-%20Project%20Home.md) | Turning a JavaFX desktop app into a web app with a trained ML symptom checker and a doctor directory | Planning | Not published yet |

## How the vault works

- **Every note has frontmatter.** `type` says what kind of note it is (`lecture`, `lesson`, `concept`, `past-paper`, `revision`, `project` and so on) and `status` tracks how finished it is (`stub`, `draft`, `done`).
- **Dashboards are built from that frontmatter.** [`00. Vault Tracker.base`](00.%20Vault%20Tracker.base) is an Obsidian Bases file whose views (Still to finish, Recently edited, University, Concepts, Projects) are embedded in the Home note and the course homes.
- **Concepts are written once.** Course and project notes link to `04. Concepts` and don't repeat the explanation.
- **Each course and project has a home note** that links to all of its lessons, exam prep and progress notes.
- **Weekly review.** Once a week I tick off progress and pick a few stubs to finish next.

## Using this vault

1. Clone the repository:
   ```bash
   git clone https://github.com/rSith/Obsidian-CS-Vault.git
   ```
2. In Obsidian, choose **Open folder as vault** and select the cloned folder.
3. Open `00. Home` and follow the links from there.

The dashboards need the **Bases** core plugin, which ships with recent versions of Obsidian. The vault is backed up to GitHub automatically by the [Obsidian Git](https://github.com/Vinzent03/obsidian-git) community plugin, which is why the commit history is a series of `vault backup` commits.

## About these notes

These are personal study notes written while I'm learning, so some are unfinished and some may contain mistakes. Corrections are welcome: please open an issue.
