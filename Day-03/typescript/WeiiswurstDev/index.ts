import fs from 'fs'
import {EOL} from 'os'

const input = fs.readFileSync("input.txt").toString().split(EOL);
const columnLength = input[0].length;

function solve(stepX: number, stepY:number) {
  let x=0, y=0;
  let treesEncountered = 0;
  while(y+1 < input.length) {
    x += stepX;
    y += stepY;
    if(getInputAt(x,y) == "#") treesEncountered++;
  }
  return treesEncountered;
}

function getInputAt(x: number, y: number) {
  return input[y][x%columnLength]
}

console.log("Teil 1:",solve(3, 1))
console.log("Teil 2:",solve(1,1)*solve(3,1)*solve(5,1)*solve(7,1)*solve(1,2));