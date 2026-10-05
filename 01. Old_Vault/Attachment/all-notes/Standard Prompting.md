>[!note] 
>A **standard prompt is** **just a direct question** or **instruction** no special formatting or technique involved.

This is the prompting technique that using every time you talk to an LLM, It's just  asking a straightforward question or giving a direct command, like:

- "Write me a JavaScript function to sort an array and remove duplicates"
- "Explain to me why thunder is so scary"

it's the foundation every other prompting technique builds on.

>[!quote] Example 
>
```
Create a prompt library application that lets users save and delete prompts.
Users should be able to:
- Enter a title and content for their prompt
- Save it to localStorage
- See all their saved prompts displayed on the page
- Delete prompts they no longer need
Make it look clean and professional with HTML, CSS, and JavaScript.
```

>[!important]
> with an unstructured prompt like this, the assistant often **adds features you didn't ask for** and **makes a lot of assumptions** introducing **more randomness than you want**, especially for code generation.

