>[!note] 
> - model is given one example of a task before performing similar tasks. It helps the model understand the expected output format and improves accuracy.

A One-Shot Prompt include :
- Task Instruction : A brief description of what the model should do.
- One Example : A single example of the desired input and output.
- New Input : The actual data for which the model should generate a response.


> [!important ] Note
> - choose your example carefully because it sets the pattern
> - make your example the majority representative, don't pick an edge case
>- include all elements you want in your output
>- combine with explicit instructions for any additionally needed clarity
>- don't overcomplicate your example to cover every case, the model will generalize that pattern

>[!qoute] Example :
>Write an engaging introduction for a blog post about remote work productivity.
>
Example:
Topic: Benefits of morning exercise
Introduction: "Picture this: It's 6 AM, your alarm goes off, and instead of hitting snooze, you lace up your sneakers. Sound impossible? Here's the thing—those who exercise before breakfast report 23% higher energy levels throughout their workday. But the real secret isn't just the exercise itself; it's what happens to your brain chemistry in those precious morning hours."
>
Now write an introduction for: Remote work productivity tips

