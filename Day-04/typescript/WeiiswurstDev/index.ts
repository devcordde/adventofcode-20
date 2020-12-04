//
//  PLEASE READ THE README.md IN MY SHARED FOLDER! 
//

import fs from 'fs'
import { EOL } from 'os'

// Read input
const input = fs
	.readFileSync('input.txt')
	.toString()
	.split(EOL);

// All eye colors
const eyeColors = ["amb","blu","brn","gry","grn","hzl","oth"]

// Field logic
type Field = {
  name:string,
  validate: (obj: string)=>boolean
}

const requiredFields: Field[] = [
  {name:"byr", validate: obj=>isValueBetween(obj,1920,2020)},
  {name:"iyr", validate: obj=>isValueBetween(obj,2010,2020)},
  {name:"eyr", validate: obj=>isValueBetween(obj,2020,2030)},
  {name:"hcl", validate: obj=>{
    if(obj.length != 7) return false;
    return obj.match(/#[0-9a-f]{6}/) != null;
  }},
  {name:"hgt", validate: obj=>{
    let len = obj.length;
    if(obj.endsWith("cm")) return isValueBetween(obj.substring(0,len-2),150,193)
    else if(obj.endsWith("in")) return isValueBetween(obj.substring(0,len-2),59,76)
    else return false;
  }},
  {name:"ecl", validate: obj=>eyeColors.includes(obj)},
  {name:"pid", validate: obj=>{
    if(obj.length != 9) return false;
    return !Number.isNaN(Number.parseInt(obj))
  }},
]

function isValueBetween(value:string,min:number,max:number):boolean {
  let x = Number.parseInt(value);
  return x >= min && x <= max;
}

//
// Using the fields/functions that are defined above
//

let current:any = {};
const passports:any[] = [];
let hasAllFieldsAmount = 0;
let allFieldsValidAmount = 0;

// Extracted logic from the for loop to also handle the last element
function handlePassport() {
  passports.push(current);
    let hasAllFields = true;
    let allFieldsValid = true;
    for(let field of requiredFields) {
      // Part 1
      if(current[field.name] === undefined) hasAllFields = false;
      // Part 2
      else if(!field.validate(current[field.name])) allFieldsValid = false;
    }
    if(hasAllFields) hasAllFieldsAmount++
    if(hasAllFields && allFieldsValid) allFieldsValidAmount++;
    current = {};
}

// Handle all passports
for (let line of input) {
	if (line == '') { // New passport begins, handle old one
    handlePassport();
    continue;
	}
	let pairs = line.split(' ');
	for (let pair of pairs) {
		let split = pair.split(':');
		let key = split[0];
		let val = split[1];
		current[key] = val;
	}
}

// Handle the last passport
handlePassport();

// Print solutions
console.log("Part 1:",hasAllFieldsAmount)
console.log("Part 2:",allFieldsValidAmount)