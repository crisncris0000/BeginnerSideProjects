'use strict';

const modal = document.querySelector(".modal");
const overlay = document.querySelector(".overlay");
const btnCloseModal = document.querySelector(".close-modal");
const btnsOpenModal = document.querySelectorAll(".show-modal");

const closeModal = function()
{
    modal.classList.add("hidden");
    overlay.classList.add("hidden");
    //Gives the exit functionality by giving them back the hidden class
}

const openModal = function()
{
    console.log("button clicked");
    modal.classList.remove("hidden"); // makes the window not hidden and pop up when clicking button
    //Can remove class or add class in the html/css
    overlay.classList.remove("hidden");

    
}

for(let i = 0; i < btnsOpenModal.length; i++)
{
    btnsOpenModal[i].addEventListener("click", openModal);
}





btnCloseModal.addEventListener("click", closeModal);
overlay.addEventListener("click", closeModal)

//checks event everywhere compared to other eventlisteners
document.addEventListener("keydown", function(e)
{
    if(e.key === "Escape")
    {
      if(!modal.classList.contains(".hidden"))
      {
       closeModal();   
      }
    }

}); 
//Handles event when key is pressed