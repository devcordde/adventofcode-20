import fs from 'fs'
import {EOL} from 'os'

const input = fs.readFileSync("input.txt").toString().split(EOL)

type BagContainList = {
  color:string,
  amount:number
}[];

type BagList = {
  [key: string]: BagContainList
}

const bags: BagList = {}

for(let line of input) {
  const split = line.split(" ");
  const contains:BagContainList = [];
  if(split[4] != "no") for(let i = 4; i < split.length; i+=4)
    contains.push({
      color:split[i+1]+" "+split[i+2], 
      amount:Number.parseInt(split[i])
    });
  bags[split[0]+" "+split[1]] = contains;
}

let part1total = 0;

for(let bag in bags) {
  if(checkBag(bags[bag])) part1total++;
}

function checkBag(containList:BagContainList):boolean {
  let hasGoldenBag = false;
  for(let contains of containList) {
    if(contains.color == "shiny gold") return true;
    else if(checkBag(bags[contains.color])) {
      hasGoldenBag = true;
      break;
    }
  }
  return hasGoldenBag;
}

function countBagsInside(bagList:BagContainList):number {
  let count = 1;
  for(let bag of bagList) {
    count += countBagsInside(bags[bag.color])*bag.amount;
  }
  return count;
}

console.log("Part 1",part1total)
console.log("Part 2",countBagsInside(bags["shiny gold"])-1)