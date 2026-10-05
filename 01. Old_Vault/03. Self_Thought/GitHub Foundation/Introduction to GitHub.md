## What is GitHub?
- GitHub is a cloud-based platform built on Git, a distributed version control system.
- GitHub simplifies collaboration and provides tools for developers to work together.

### Repositories
> **Definition:** Contains all project files and each file's revision history

**Cloning a Repository:**
```
git clone <repo-url>
cd <repo-name>
```

### Gists
**What are Gists?**
- Share code snippets, notes or small pieces of information
- Lightweight alternative to full repositories(can fork, clone, and version-control)

**Key Features:**

- Public Gists (discoverable via search)
- Secret Gists (not searchable but accessible via URL)
- Version control for edits
- Can be forked and cloned
- Support Markdown formatting
- Can be embedded in websites/blogs
- Collaborative capabilities

**Use Cases:**

- Share quick code examples
- Store personal scripts or configs
- Create templates
- Share error logs/debugging info
- Embed code in blogs/docs

>[!important] 
>Gists are NOT fully private - even secret gists can be accessed with the link. Never store sensitive data like passwords, API keys, or secrets.

**Limitations:**

- Not truly private (anyone with URL can access)
- Best for small snippets, not large projects
- Create: Fork icon at top-right of gist page

---
### Wikis
> Every repository on GitHub.com comes equipped with a section for hosting documentation, called a wiki.

**Purpose:**
- Repository documentation hosting - share long-form content about your project, such as how to use it, how you designed it, or its core principles

**README vs Wiki**
- README file quickly tells what your project can do
- wiki provide additional documentation.

## Components of the GitHub Flow

### Branches

>**Definition:** Independent lines of development for code changes.
>- Branches let you make changes without affecting the default branch.

**File States:**

- **Untracked:** Initial file state - not yet part of repository
- **Tracked:** Git is monitoring the file; can exist in substates:
    - **Unmodified:** No changes since last commit
    - **Modified:** Changed since last commit, but not staged
    - **Staged:** Modified and added to staging area (ready to commit)
    - **Committed:** File in repository database (latest version)

### Pull Requests

**Definition:** Mechanism to signal commits from one branch are ready to merge into another

**Process:**

- Submitter asks reviewers to verify code
- Reviewers provide feedback
- Updates made based on feedback
- Approval and merge when confident

### GitHub Flow (Recommended for continuous delivery)

**6-Step Process:**

1. Create a branch for changes/features/fixes
2. Make updates in branch; can deploy to test before merging
3. Open a pull request to invite feedback and review
4. Review comments and make necessary updates based on feedback
5. Get approval and merge pull request into main branch
6. Delete branch to keep repository clean and avoid outdated branches

### Git Flow (Alternative for release-driven environments)

**Structured branching model using:**

- **master:** Always production-ready code
- **develop:** Latest development work for next release
- **feature/*:** New features (branched from develop, merged back)
- **release/*:** Prepares new production release (from develop; testing & minor bug fixes)
- **hotfix/*:** Patches production issues (branched from master, merged to both master and develop)

**Process:**

1. Developers create feature branches from develop
2. Release branch created from develop for release preparation
3. Bug fixes added to release branch; development continues uninterrupted
4. Release branch merged to master and tagged with version number
5. Release branch merged back to develop to keep in sync
6. Critical production bugs: hotfix branch from master, merged to both master and develop

**When to Use Git Flow:** Better for release-driven or scheduled deployments