
//  PLEASE READ THE README.md IN MY SHARED FOLDER! 

import fs from 'fs'
import { EOL } from 'os'

const input: number[] = fs.readFileSync("./input.txt").toString().split(EOL).map(num=>Number.parseInt(num))

for(let i = 0; i < input.length; i++) {
  let x = input[i]
  for(let j = i; j < input.length; j++) {
    let y = input[j]

    if(x+y == 2020)
      console.log("Part 1: "+(x*y))
    for(let k = j; k < input.length; k++) {
      let z = input[k]

      if(x + y + z == 2020)
        console.log("Part 2: "+(x*y*z))
    }
  }
}