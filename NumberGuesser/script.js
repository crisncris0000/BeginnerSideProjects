'use strict';
/*
document.querySelector(".message").textContent = "Correct Number";

console.log(document.querySelector(".message").textContent);

document.querySelector(".number").textContent = 13;
document.querySelector(".score").textContent = 12;

document.querySelector(".guess").value = 23;
console.log(document.querySelector(".guess").value);
*/

let secretNumber = Math.trunc(Math.random()* 20) + 1;
let score = 20;
let highScore = 0;

const displayMessgae = function(message)
{
    document.querySelector(".message").textContent = message; 
    
}

document.querySelector(".check").addEventListener("click", function()
{
    const guess = Number(document.querySelector(".guess").value);
    console.log(guess, typeof guess);

    if(!guess)
    {
      displayMessgae("No number!!");
    }
    else if(guess === secretNumber)
    {
        displayMessgae("Correct Number");
        score++;
        document.querySelector(".score").textContent = score;

        document.querySelector("body").style.backgroundColor = "#0e9948";
        
        document.querySelector(".number").style.Number = "30rem";
        document.querySelector(".number").textContent = secretNumber;

        if(score > highScore)
        {
            highScore = score;
            document.querySelector(".highscore").textContent = highScore;
        }

    }
    else if(score > 0 ){
        //if guess is different, meaning that you wont have to use if else if statements repeated code
    if(guess !== secretNumber)
    {
        displayMessgae(guess > secretNumber ? "Too high" : "Too low") // if then else
        score--;
        document.querySelector(".score").textContent = score;
    }
}
else
{
   displayMessgae("You lose the game");
    document.querySelector("body").style.backgroundColor = "#b0001d";
   
}
})

document.querySelector(".again").addEventListener("click", function()
{
   score = 20;
   document.querySelector(".score").textContent = score;
   displayMessgae("Start guessing...");
   document.querySelector("body").style.backgroundColor = "#222";
   secretNumber = Math.trunc(Math.random()* 20) + 1;
   document.querySelector(".number").textContent = "?";
   document.querySelector(".guess").value = ""
   document.querySelector(".number").style.Number = "15rem";
})


