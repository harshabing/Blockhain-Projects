pragma solidity ^0.4.19;

//pragma experimental ABIEncoderV2;
contract mailing{
    
    
    string UserName;
    string PassWord;
    
    mapping(string => string) Login;
    mapping(string => address) TheOwner;
    address[] AccreditedOwners;
    mapping(string=>uint256[]) timestamp;
    
    function RegisterCredentials(string User, string Pass, address MainOwner) public returns(string){
        
        Login[User] = Pass;
        TheOwner[User] = MainOwner;
        
        AccreditedOwners.push(MainOwner);
        
        return "Securely Stored, I won't let you and your Mails Down :)";
        
        
    }
    
  
    
    function SignIn(string UserLogin,string PassLogin,address OwnerLogin) public returns(string) {
        
        if(TheOwner[UserLogin]==OwnerLogin && keccak256(abi.encodePacked((Login[UserLogin]))) == keccak256(abi.encodePacked((PassLogin)))) {
            timestamp[UserLogin].push(block.timestamp);
            return "You are Authorized to Login";
        }
    }
    
    
    function LoginLog(string UserLog,address OwnerLog) public view returns(uint256[]) {
        
        if(TheOwner[UserLog]==OwnerLog) {
            return timestamp[UserLog];
        }
    }
    
    
    
        
    
}