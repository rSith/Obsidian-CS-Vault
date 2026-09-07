![[Pasted image 20260904132141.png]]
1. Deployment
2. Modeling
3. Data & Scoping
4. MLOps(Machine Learning Operations)

---
# Deployment 
Deploying a model is **not the finish line**. it's roughly the halfway point. The real work (monitoring, maintaining, retraining) starts _after_ deployment.

Two broad categories of deployment challenges:
1. **ML / Statistical issues** : data changing after deployment
2. **Software engineering issues** : how to actually build and serve the prediction system
---
### 1. ML/Statistical Issues: Concept Drift & Data Drift
> What happens if the **data changes after** your system is deployed?

- **Data drift:** The distribution of inputs X changes
	- ***Speech recognition (data drift):** New phone models → different microphones → audio sounds different. Language evolves slowly (gradual drift).*
- **Concept Drift:** The mapping from X -> Y changes (same input, different correct output)
	- ***Housing prices (concept drift):** Same size house (X) becomes more expensive over time (Y) due to inflation → mapping X→Y changed, not the distribution of house sizes.*
	
>[!abstract] Practical takeaway
- Use a **test set with recent data**, not just historical data to check the model still works on current conditions.
- After deployment : keep monitoring whether data has changed, and be ready to retrain/Update the model.

### 2. Software Engineering Issues
> When designing the **prediction service** (input X -> output Y)

> [!todo] 
> 1. **Real-time vs. Batch prediction?**
> 	- Real time : Speech recognition -> needs response in real time.
> 	- Batch: Hospital system process patient records overnight. 
> 2. **Where does it run?**
> 	- **Cloud** : more compute, better accuracy 
> 	- **Edge** : needed when internet not have.
> 	- **Browser** : Web ML tools
> 3. **Computer resources** (CPU/GPU/Memory)
> 4. **Latency & Throughput**
> 	- **Latency**: time to respond to one query
> 	- **Throughput**(QPS-queries per second): how many requests the system must handle give variable compute
> 5. **Logging:** Log data for analysis/review **and** to build future retraining datasets.
> 6. **Security & Privacy**

