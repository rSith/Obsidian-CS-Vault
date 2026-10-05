---
type: project
stage: active
course:
repo:
tags: [project, web, ml]
---
# PlantFolio — *"Where Your Plants Tell Their Story"*
> [!abstract] Goal
> Plant Care & Exchange Community (assigned web coursework): Instagram for plants + Goodreads for plant collectors + a plant-care manager. Built with ML extensions as a portfolio piece.
> **Team:** 7 members, I'm the project leader.

## Core MVP features
- [ ] User auth, profiles (bio, location)
- [ ] Gardens → plant sub-profiles (plant CRUD)
- [ ] Search / filter by type, location, status
- [ ] Health status, "last watered", simple care reminders
- [ ] **Plant exchange listings** (browse, mark interest, show contact). The lecturer requires this feature.
- [ ] User ratings / reputation
- [ ] Mobile-responsive design

## Tech stack
HTML5 + CSS3 + JavaScript · Flask + SQLAlchemy · MySQL · TensorFlow/Keras (ML features)

**Database (8 tables):** users, user_gardens, plants, plant_species, plant_history, plant_listings, user_interests, user_ratings

## ML extensions
- [ ] Plant recognition with pre-trained MobileNetV2
- [ ] Disease detection CNN trained on the PlantVillage dataset (Colab GPU)
- [ ] Stretch: smart care recommendations · exchange matching via embeddings · AI chatbot

## Marking
Functionality 70% · UX 15% · Code quality 10% · Documentation 5%

## Related notes
- [[HTML Basics]] · [[Neural Network]] · [[Classification]]

## Log
-
