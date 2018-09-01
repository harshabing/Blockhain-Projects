pragma solidity ^0.4.18; contract SimpleWallet {
// Address is the owner
address owner;
struct WithdrawlStruct {
address to;
uint amount;
}
// Create an object for Senders
struct Senders {
bool allowed;
uint amount_sends;
mapping(uint => WithdrawlStruct) withdrawls;
}
// Mapping to determine if the Sender is allowed to send funds
mapping(address => Senders) isAllowedToSendFundsMapping;
// Events for Deposit and Withdrawals
event Deposit(address _sender, uint amount);
event Withdraw(address _sender, uint amount, address _beneficiary);
// Set the owner as soon as the wallet is created
constructor() public {
owner = msg.sender;
}
// Check if the caller is allowed to send the messages.
modifier allowedToSend() {
require(isAllowedToSend(msg.sender));
_;
}
// This anonymous function is called when the contract receives
// funds from an address that is allowed to send funds
// The "msg.sender" needs to be the Owner/Senders
// and allowed to send funds to deposit them to the wallet
// Also emit an event called Deposit and declare the "msg.sender"
// and the value deposited.
function() allowedToSend public payable {
emit Deposit(msg.sender, msg.value);
}
// Someone that is allowed to deposit funds is allowed to send
// In this case, it is the owner or the boolean mapping is true for the caller
// Their balance must be higher than the amount
// If it goes through, we emit a withdraw event and return the balance
function sendFunds(uint amount, address receiver) public allowedToSend returns (uint) {
require(address(this).balance >= amount);
receiver.transfer(amount);
emit Withdraw(msg.sender, amount, receiver);
// Log each withdrawl, receiver, amount
isAllowedToSendFundsMapping[msg.sender].amount_sends++;
isAllowedToSendFundsMapping[msg.sender].withdrawls[
isAllowedToSendFundsMapping[msg.sender].amount_sends ] = WithdrawlStruct(receiver, amount);
return  address(this).balance;
}
modifier isOwner(){
require(msg.sender == owner);
_;
}
// Allowed to send funds when the boolean mapping is set to true
function allowAddressToSendMoney(address _address) public isOwner {
isAllowedToSendFundsMapping[_address].allowed = true;
}
// Not allowed to send funds when the boolean mapping is set to false
function disallowAddressToSendMoney(address _address) public isOwner {
isAllowedToSendFundsMapping[_address].allowed = false;
}
// Check function which returns the boolean value
function isAllowedToSend(address _address) public constant returns (bool) {
return isAllowedToSendFundsMapping[_address].allowed || _address == owner;
}
// Check to make sure the msg.sender is the owner
// And it will suicide the contract and return funds to the owner
function killWallet() public isOwner {
selfdestruct(owner);
}
} 
