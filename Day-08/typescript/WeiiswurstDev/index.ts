import fs from 'fs'
import {EOL} from 'os'

const input = fs.readFileSync("input.txt").toString().split(EOL)

const linesVisited = new Set<number>();

let part = 1;

function exec(acc:number,line:number):any {
  if(line >= input.length) return false;
  if(linesVisited.has(line)) {
    if(part == 1) console.log("Part 1",acc)
    return true;
  }
  linesVisited.add(line)
  const split = input[line].split(" ");
  const exp = split[0]
  if(exp=="nop") {
    return exec(acc,line+1)
  }
  else if(exp=="jmp") return exec(acc,line+parseNumber(split[1]))
  else if(exp=="acc") {
    acc+=parseNumber(split[1])
    return exec(acc,line+1)
  }
}

function parseNumber(number:string):number {
  if(number[0]=="+") return Number.parseInt(number.substring(1,number.length))
  else return Number.parseInt(number)
}

exec(0,0)

part = 2;

console.log("No solution for Part 2 (yet). I might add one when I find time for it.")