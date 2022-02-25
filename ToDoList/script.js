"use script"
window.onload = function (){
    const inputTask = document.getElementById("input-task");
    const addTask = document.getElementById("add-task-button");
    const ulList = document.getElementById("task-list");


    addTask.addEventListener("click", function() {
        const inputText = inputTask.value;
        const listTag = document.createElement("li");
        const deleteButton = document.createElement("button");
        deleteButton.className = "delete-btn";

        const checkbox = document.createElement("input");
        checkbox.setAttribute("type", "checkbox");
        checkbox.className = "checkbox";
        checkbox.onclick = function() {
            if(checkbox.checked == true) {
                span.style.textDecoration = "line-through";
            } else{
                span.style.textDecoration = "none";
            }
        }

        const span = document.createElement("span");
        span.className = "task";


        span.innerText = inputText
        listTag.append(span);
        ulList.appendChild(listTag);
        listTag.appendChild(deleteButton);
        listTag.appendChild(checkbox);


        deleteButton.addEventListener("click", function (){
           ulList.removeChild(listTag);
        });



    });


}