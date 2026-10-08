---
type: project-note
project: PlantFolio
status: stub
tags: [project, ml]
---
# PlantFolio Week 12 — Training the Models
> [!info] [[PlantFolio - Project Home]] · Stage M · 21–27 Dec 2026
> **Study notes:** [[05.00 Machine Learning Service]] · **Revision:** [[05.99 Summary - Machine Learning Service]]

> [!abstract] Week goal
> A disease model that reaches at least 90 % test accuracy, measured honestly, and a labelled photo set for plant recognition. Training runs on Google Colab.

## Tasks
- [ ] **ML-04** Baseline CNN from scratch → [[05.03 Convolutional Neural Networks]]
- [ ] **ML-05** Fine-tune MobileNetV2 with augmentation, target ≥ 90 % → [[05.04 Transfer Learning with MobileNetV2]]
- [ ] **ML-06** Evaluate on the test set and on 20–30 real phone photos → [[05.06 Evaluating an Image Classifier]]
- [ ] **ML-08** Labelled photo set for recognition; measure top-1 and top-3
- [ ] **ML-15** Guidance text for each disease class

## Learn this week
Keras transfer learning, precision and recall, confusion matrices.

## Checks
- [ ] The test set was used once, after all choices were made on the validation set
- [ ] Accuracy on phone photos is reported beside test-set accuracy, even if it is much lower
- [ ] The confusion matrix shows which classes are confused with each other
- [ ] The model file, its class list and the notebook are saved; the notebook is committed without large outputs
- [ ] Every experiment is noted: settings and validation accuracy

---
## Work log
Step sections are added here as each step is finished.
