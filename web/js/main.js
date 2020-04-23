/* Check if navigation is open or closed */
let toggleNavStatus = false;

let toggleNav = function() {
    /* Get all HTML elements we want to change styling of */
    let getSidebar = document.querySelector(".nav-sidebar");
    let getSidebarUl = document.querySelector(".nav-sidebar ul");
    let getSidebarTitle = document.querySelector(".nav-sidebar span");
    let getSidebarLinks = document.querySelectorAll(".nav-sidebar a");

    /* Check toggle nav status */
    if (toggleNavStatus === false) {
        getSidebar.style.width = "272px";
        getSidebarUl.style.visibility = "visible";
        getSidebarTitle.style.opacity = "0.7";
        
        for (let i = 0; i < getSidebarLinks.length; i ++) {
            getSidebarLinks[i].style.opacity = "1";
        }

        toggleNavStatus = true;
    } else if (toggleNavStatus === true) {
        getSidebar.style.width = "50px";
        getSidebarUl.style.visibility = "hidden";
        getSidebarTitle.style.opacity = "0";
        
        for (let i = 0; i < getSidebarLinks.length; i ++) {
            getSidebarLinks[i].style.opacity = "0";
        }

        toggleNavStatus = false;
    }


}