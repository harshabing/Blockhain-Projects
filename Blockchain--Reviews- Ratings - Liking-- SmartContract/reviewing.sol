pragma solidity ^0.4.18;
pragma experimental ABIEncoderV2;


contract reviewing {
    
    
    mapping(address => mapping(uint256 =>string)) public reviews;
    mapping(address => mapping(uint256 =>uint8)) public reviewlike;
    mapping(address=>mapping(string=>uint8)) likerlimit;
    mapping(address => uint256) YourCounter;
    address owner;
    constructor() public {
        
        owner = msg.sender;
        
    }
    
    
   function WriteReview(string tell) public returns (uint256,address,bool) {
      address reviewer=msg.sender;
           
        YourCounter[reviewer] +=1;
       
       reviews[reviewer][YourCounter[reviewer]] = tell;
       reviewlike[reviewer][YourCounter[reviewer]] =0;
       
       return (YourCounter[reviewer],reviewer,true);
      } 
   
   
   function RemoveReview(uint256 RemoveId,address RemoveAddress) public returns(bool) {
       
       if(msg.sender==owner) {
           
           delete reviews[RemoveAddress][RemoveId];
            return true;
       }
       return false;
   }
       
     function SeeReview(address getting, uint256 __id) public view returns(string) {
         
         return(reviews[getting][__id]);
     }
     
     function YourCount(address _add) public view returns(uint256) {
         return (YourCounter[_add]);
     }
     
     function LikeReview(address likeadd,uint256 __id) public returns(string say){
         
         if(likerlimit[msg.sender][reviews[likeadd][__id]] ==0)  {
             reviewlike[likeadd][__id] +=1;
             likerlimit[msg.sender][reviews[likeadd][__id]] =1;
             say= "Either you like his review or You must be really liking that Person :p";
             return say;
            }
        
         return "oops";
       
     }
     
     
   /*  function SearchComment(string str) public returns(string) {
         
         
         
     } */
     
     
     
   }
   
   
   
    
