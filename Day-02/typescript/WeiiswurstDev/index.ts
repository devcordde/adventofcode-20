
// Please read the README.md in my shared folder!

import fs from 'fs'
import { EOL } from 'os'

const input = fs.readFileSync("input.txt").toString().split(EOL);

let validEntries = 0;
let part2validEntries = 0;

for(let entry of input) {
  let split = entry.split(" ")
  let amounts = split[0].split("-").map(num=>Number.parseInt(num))
  let limitedChar = split[1].substring(0,1)
  let pass = split[2]

  let actualAmount = 0;
  for(let char of pass) {
    if(limitedChar == char) actualAmount++;
  }
  if(actualAmount >= amounts[0] && actualAmount <= amounts[1]) {
    validEntries++;
  }

  let correctIndexes = 0;

  for(let index of amounts) {
    let char = pass[index-1];
    if(char == limitedChar)
      correctIndexes++;
  }
  if(correctIndexes == 1) part2validEntries++;
}

console.log("Part 1",validEntries)
console.log("Part 2",part2validEntries)