// DOM Elements
const algorithmSelect = document.getElementById("algorithm");
const arraySizeInput = document.getElementById("array-size");
const delayInput = document.getElementById("delay");
const startButton = document.getElementById("start-btn");
const arrayContainer = document.getElementById("array-container");
const sizeValue = document.getElementById("size-value");
const delayValue = document.getElementById("delay-value");

let array = [];
let delay = 100;
let arraySize = 50;

// Update the size and delay values
sizeValue.textContent = arraySize;
delayValue.textContent = delay + 'ms';

arraySizeInput.addEventListener('input', (e) => {
    arraySize = e.target.value;
    sizeValue.textContent = arraySize;
    generateArray();
});

delayInput.addEventListener('input', (e) => {
    delay = e.target.value;
    delayValue.textContent = delay + 'ms';
});

startButton.addEventListener('click', () => {
    const selectedAlgorithm = algorithmSelect.value;
    fetchSortingData(selectedAlgorithm);
});

// Function to generate array with random values
function generateArray() {
    array = [];
    arrayContainer.innerHTML = ''; // Clear previous bars
    for (let i = 0; i < arraySize; i++) {
        const barHeight = Math.floor(Math.random() * 300) + 10; // Random height between 10 and 310
        const bar = document.createElement("div");
        bar.classList.add("bar");
        bar.style.height = barHeight + 'px'; // Set height of the bar based on the value
        arrayContainer.appendChild(bar);
        array.push(bar);
    }

    console.log("Array Generated: ", array);
}

// Function to fetch data from backend (GET API)
async function fetchSortingData(algorithm) {
    const response = await fetch(`http://localhost:8080/api/sort?algorithm=${algorithm}&size=${arraySize}&delay=${delay}`);
    const sortedData = await response.json();
    console.log(sortedData);  // Debugging: Check what data we received
    visualizeSorting(sortedData.steps);
}

// Function to visualize sorting steps
async function visualizeSorting(steps) {
    const bars = Array.from(arrayContainer.children);

    for (let i = 0; i < steps.length; i++) {
        const step = steps[i];

        // Update the height of the bars based on the step array
        for (let j = 0; j < bars.length; j++) {
            bars[j].style.height = step[j] + 'px'; // Update the bar height
            bars[j].classList.remove("selected", "swapped", "sorted"); // Remove previous state

            // Highlight the bar that's currently being selected
            if (i === j) {
                bars[j].classList.add("selected");
            }
        }

        // Highlight swapped elements (if any)
        if (i > 0) {
            const previousStep = steps[i - 1];
            for (let k = 0; k < bars.length; k++) {
                if (step[k] !== previousStep[k]) {
                    bars[k].classList.add("swapped");
                }
            }
        }

        // Wait for the delay before moving to the next step
        await sleep(delay);
    }

    // After sorting is done, mark all bars as sorted
    bars.forEach(bar => bar.classList.add("sorted"));
}

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

generateArray();