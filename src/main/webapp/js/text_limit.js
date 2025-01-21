const limits = document.querySelectorAll(".txt-limits");
const lens = 24;
limits.forEach((element) => {
    let strs = element.textContent;
    if (strs.length > lens) {
        element.innerHTML =  strs.substring(0, lens) + "…" ;
    }
});

const limits_min = document.querySelectorAll(".txt-limits-min");
const lens_min = 12;
limits_min.forEach((element) => {
    let strs = element.textContent;
    if (strs.length > lens) {
        element.innerHTML =  strs.substring(0, lens_min) + "…" ;
    }
});