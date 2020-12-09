import fs from 'fs'
import {EOL} from 'os'

const input = fs.readFileSync("input.txt").toString().split(EOL).sort()
console.log(input.length)

function toBin(x:string,binaryOne:string) {
  let current = 0;
  for(let i = 0; i < x.length; i++)
    current += (x[i]==binaryOne?1:0)*(Math.pow(2,x.length-i) / 2)
  return current;
}


let maxSeatId = 0;
let lastCol = 0;
let ignoreRow = -1;
for(let line of input) {
  let row = toBin(line.substring(0,7),"B")
  let col = toBin(line.substring(7,line.length),"R")
  //console.log(line,line.substring(0,7),row,line.substring(7,line.length),col)
  let seatId = row*8+col;
  if(seatId > maxSeatId) maxSeatId = seatId;

  if(col-lastCol == 0) ignoreRow = row-1;
  else if(row==ignoreRow) ignoreRow = -1;
  // My poor attempt at part 2, I fiddled with this if statement until it spat out numbers in "Part 2", then tried the sensible numbers.
  // Do not repeat.
  //else if(col-lastCol != 1 && col-lastCol != -7  && row > 2) console.log("Part 2",seatId)
  lastCol = col;
}

console.log("Part 1",maxSeatId)
console.log("No solution for part 2. Please use another solution.")