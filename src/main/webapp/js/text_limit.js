const limits = document.querySelectorAll(".txt-limits");
const lens = 24;
limits.forEach((element) => {
    let strs = element.textContent;
    if (strs.length > lens) {
        element.innerHTML =  strs.substring(0, lens) + "…" ;
    }
});