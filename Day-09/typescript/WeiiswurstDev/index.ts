import {readFileSync} from 'fs'
import {EOL} from 'os'

const input = readFileSync("input.txt").toString().split(EOL).map(line=>Number.parseInt(line))

const last25:number[] = []
let counter = 0;
const sums:number[] = [];

let part1 = 0;

for(let num of input) {
  if(last25.length != 25) {
    last25.push(num)
    if(last25.length == 25)
      for(let x of last25)
        for(let y of last25)
          sums.push(x+y)
  } else {
    if(!sums.includes(num)) {
      part1 = num;
      break;
    }
    last25[counter] = num;
    for(let i = 0; i < 25; i++) {
      sums[i+counter*25] = num+last25[i];
    }
    counter++;
    if(counter==25) counter=0;
  }
}

console.log("Part 1",part1)

for(let i = 0; i < input.length; i++) {
  let sum = input[i];
  let j = i;
  let smallest = Number.MAX_VALUE;
  let largest = Number.MIN_VALUE;
  while(sum < part1) {
    j++
    sum += input[j]
    smallest = Math.min(smallest,input[j])
    largest = Math.max(largest,input[j])
  }
  if(sum == part1) {
    console.log("Part 2",smallest+largest)
    break;
  }
}