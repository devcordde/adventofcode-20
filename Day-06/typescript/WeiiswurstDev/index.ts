import fs from 'fs'
import {EOL} from 'os'

const input = fs.readFileSync("input.txt").toString().split(EOL)

let atLeastOne = new Set<string>();
let everyone = new Set<string>();
let totalPart1 = 0, totalPart2 = 0;
let newLine = true;

for(let line of input) {
  if(line == "") {
    totalPart1 += atLeastOne.size;
    atLeastOne.clear();
    totalPart2 += everyone.size;
    everyone.clear();
    newLine = true;
    continue;
  }
  for(let char of line) atLeastOne.add(char)

  if(newLine) {
    for(let char of line) everyone.add(char);
    newLine = false;
  } else 
    for(let char of everyone.values())
      if(!line.includes(char))
        everyone.delete(char)
}

console.log("Part 1",totalPart1)
console.log("Part 2",totalPart2)