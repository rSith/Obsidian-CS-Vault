>[!note]
>Exploratory Data Analysis(EDA) is an important step in data analysis where we **explore, summarize,** and **visualize data** to **understand its structure, data patterns, identify anomalies, test assumptions, and check relationships between variable.**

Importance:
- Clear understanding of the dataset, including the number of features, data types and data distribution.
- Reveals patterns and relationships between different variables in the data.
- Identifies errors and outliers.
- Supports selecting suitable modelling techniques for better results.

## Types of Exploratory Data Analysis
### Univariate Analysis
> Studies one variable at a time to understand its characteristics and distribution.

- **Histogram**: How data values are distributed
- **Box Plots**: Help detect outliers and show data spread
- **Bar Charts**: Used for categorical variables.

### Bivariate Analysis
> Study relationship between two variables to understand how they interact or influence each other.

- **Scatter plots**: Relationship between two numerical variables.
- **Correlation coefficient**: Measurement about the strength of the relationship between variables.
- **Cross-tabulation**: Relationship between two categorical variables.
- **Covariance**: How two variables change together.
- **Line Graph**: Compare two variables over time to identify trends.

### Multivariate Analysis
> Studies three or more variables  together to understand complex relationships within the dataset.

- **Pair plots**: Show relationship between multiple variables at once.
- **Principle Component Analysis(PCA)**: Reduces dimensionality while preserving important information.
- **Spatial analysis**: Analyze geographical patterns using maps and location-based data.

## Steps for Performing Exploratory Data Analysis 
>[!important] Tools
>- Data Manipulation : [[Pandas]]
>- Data Visualization : [[Matplotlib]], [[Seaborn]], [[Plotly]]

### Step 01: Understanding the Problem and the Data
> Fully understand the problem we're solving and the data we have.

- Goal or Problem we are trying to solve
- Variables in the dataset and what do they represent
- Available data types(numerical, categorical, texts)
- Any data quality issues or limitations?
---
### Step 02: Importing and Inspecting the Data
> Load the dataset into tools like Python or R and inspect it.

- Load the dataset
- Check the number of rows and columns.
- Identify missing values
- Verify data types
- Look for errors, invalid values or unusual data points.
---
### Step 03: Handling Missing Data

### Step 04: Exploring Data Characteristics
### Step 05: Performing Data Transformation
### Step 06: Visualizing Relationship of Data
### Step 07: Handling Outliers
### Step 08: Communicate Findings and Insights
