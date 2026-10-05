---
type: lecture
course: GitHub Foundations
lecture: 1
status: done
tags: [git]
---
# What is Version Control
> [!info] GitHub Foundations · Next: [[Introduction to GitHub]]

>[!note]
>- Version control system (VCS) is a program or set of programs that tracks changes to a collection of files.
>- Goal : 
>	- VCS is easily recall earlier versions of individual files or of the entire project
>	- Allow several team members to work on a project even on the same files. at the same time without affecting each other's work

 - Version control is often discussed as part of **software configuration management (SCM)**

**Key Capabilities of Version Control :**

- See all changes made to your project, when they were made, and who made them
- Include messages explaining the reasoning behind each change
- Retrieve past versions of the entire project or individual files
- Create branches to work on features experimentally
- Attach tags to mark versions (like releases)

**About Git :**

- fast, free, open-source, and highly scalable VCS created by Linus Torvalds
- It's a **Distributed system** - complete project history is stored on your local computer and shared when you push to a server

**Important Git Terminology :**

- **Working tree:** The project directories and files you're editing
- **Repository:** The hidden `.git` folder storing all project history
- **Hash:** A unique identifier for file contents
- **Commit:** Saving a snapshot of your changes with a message
- **Branch:** A separate line of development(main branch is default)
- **Remote:** A reference to another repository(called "origin")

**Git vs GitHub :**
- **Git** is the version control system itself
- **GitHub** is a cloud platform built on Git that adds collaboration features like pull requests, issues, discussions, and actions

---
## Basic Git Commands
**git status**
- Displays the state of the working tree and of the staging area. It lets inspect modified, staged and untracked files so you can decide what to do next.

**git add**
- Use to add file contents to the staging area.
- All changes you stage with git add are stored in the staging area until you commit them

**git commit**
- After staged changes for commit, you can save your work to a snapshot by using git commit command.

**git log**
- allows to see information about previous commits.
- each commit has a message attached to it (a commit message), and the git log command prints information about the most recent commits

**git help**
- to get information about all the commands `git <command> --help`

---
