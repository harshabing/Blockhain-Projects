pragma solidity ^0.4.0;

import "browser/ERC20.sol"; // This is how you import "here goes the path to the file, in my case it was in remix ide,browser folder."

contract FueledToken is ERC20 {        // extending er20 contract.
    string public constant symbol = "FLT";          // A token symbol, name, decimals,totalSupply are must according 
                                                    //to ethereum's ERC20 token 
    string public constant name = "My First Token";
    uint8 public constant decimals = 18;
    
    uint private constant __totalSupply = 1000;
    mapping (address => uint) private __balanceOf;
    mapping (address => mapping (address => uint)) private __allowances; // two mappings used.
    
    constructor() public {                               // in previous versions we were allowed to create constructors using function.
                                                         // but recently it changed. We need to use "constructor() {..}" way
            __balanceOf[msg.sender] = __totalSupply;
    }
    
    function totalSupply() constant public returns (uint _totalSupply) {   // getter function for getting totalSupply output.
        _totalSupply = __totalSupply;
    }
    
    function balanceOf(address _addr) public constant returns (uint balance) {  // getter function for getting balance output.
        return __balanceOf[_addr];
    }
    
    function transfer(address _to, uint value) public returns (bool success) {     // standard transfer function implementation.
        if (value > 0 && value <= balanceOf(msg.sender)) {                          // making sure we are not sending 0 amount and 
                                                                                    // sending amount is lesser or equal to amount we have.
            __balanceOf[msg.sender] -= value;                                       // subtracting our balance with that value
            __balanceOf[_to] += value;                                              // adding that value to account
            return true;
        }
        return false;                               
    }
    
    function transferFrom(address _from, address _to, uint _value) public returns (bool success) {
        if (__allowances[_from][msg.sender] > 0 &&                  
            _value > 0 &&
            __allowances[_from][msg.sender] >= _value && 
            __balanceOf[_from] >= _value) {
            __balanceOf[_from] -= _value;
            __balanceOf[_to] += _value;
            
            __allowances[_from][msg.sender] -= _value;
            return true;
        }
        return false;
    }
    
    function approve(address _spender, uint _value) public returns (bool success) {
        __allowances[msg.sender][_spender] = _value;
        return true;
    }
    
    function allowance(address _owner, address _spender) public constant returns (uint remaining) {
        return __allowances[_owner][_spender];
    }
}
