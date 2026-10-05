>[!note]
> - Similar to [[Standard Prompting]].
> - Model perform tasks without any task-specific examples, relying on knowledge learned during pre-training.

**What is the Differences from STANDARD PROMPTING :**
> All zero-shot prompts are standard prompts, but not all standard prompts are zero-shot

| Standard Prompting                                               | Zero Prompting                                                                                                                                                         |
| ---------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| A broad or casual request.                                       | A direct, Structured instruction without any previous demonstration.                                                                                                   |
| Relies entirely on the AI to guess the format, length, and tone. | Usually, contains clear task rules. Relies strictly on the AI's pre-trained knowledge.                                                                                 |
| *"Find the phone numbers in this text."*                         | *"Extract all phone numbers from the text below. Output them strictly as a comma-separated list. Do not include any names or introductory text.\n\nText: Insert Text"* |
>[!quote] Example :
>Create a prompt library application in HTML, CSS, and JavaScript.
Create an HTML page with a form containing fields for the prompt title and content
Add a save prompt button that saves to localStorage
Display saved prompts in cards
Each prompt card should show the title, a content preview of a few words, and a delete button
Deleting should remove the prompt from localStorage and update the display
Style it with CSS to look clean and modern with a developer theme
Include all HTML structure, CSS styling, and JavaScript functionality in their own files, but that can be run immediately and includes no other features.
