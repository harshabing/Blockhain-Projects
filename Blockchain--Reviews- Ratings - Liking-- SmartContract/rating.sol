pragma solidity ^0.4.18;
//pragma experimental ABIEncoderV2;


contract reviewing {
    
    
    mapping(address => mapping(uint256 =>string)) public reviews; // This is for storing the review w.r.t address and review count from that address
    mapping(address => mapping(uint256 =>uint8)) public reviewlike; // This is for storing if a particular person has liked a comment for not using that person's account address and review count and liked or not(0 for not liked and 1 for liked)
    mapping(address=>mapping(string=>uint8)) likerlimit; // An address can like a review only once. Storing if that address has already liked.
    mapping(address => uint256) YourCounter; // Total reviews
    mapping(uint256 =>string) searchreview; // Unique Id for each review.
    mapping(uint256=>address)uniquereview; // Unique Id's w.r.t addresses.
    address owner;
    uint256 countVar=0; 
    constructor() public {
        
        owner = msg.sender;
        
    }
    
    // function to write the review
   function WriteReview(string tell) public returns (uint256,address,bool) {
      address reviewer=msg.sender;
           
        YourCounter[reviewer] +=1;
       
       reviews[reviewer][YourCounter[reviewer]] = tell;
       reviewlike[reviewer][YourCounter[reviewer]] =0;
       countVar++;
       searchreview[countVar] = tell;
       uniquereview[countVar] = msg.sender;
       
       return (YourCounter[reviewer],reviewer,true);
      } 
   
   
    // function to delete the review
   function RemoveReview(uint256 RemoveId,address RemoveAddress) public returns(bool) {
       
       if(msg.sender==owner) {
           
           delete reviews[RemoveAddress][RemoveId];
            countVar--;
            return true;
            YourCounter[RemoveAddress] -=1;
       }
       return false;
   }
       
    // function to see the review
     function SeeReview(address getting, uint256 __id) public view returns(string) {
         
         return(reviews[getting][__id]);
     }
     
    // function to get total reviews
     function YourCount(address _add) public view returns(uint256) {
         return (YourCounter[_add]);
     }
     
    // function to like the review
     function LikeReview(address likeadd,uint256 __id) public returns(string say){
         
         if(likerlimit[msg.sender][reviews[likeadd][__id]] ==0)  {
             reviewlike[likeadd][__id] +=1;
             likerlimit[msg.sender][reviews[likeadd][__id]] =1;
             say= "Either you like his review or You must be really liking that Person :p";
             return say;
            }
        
         return "oops";
       
     }
     
     
        // function to search for the review
         function SearchComment(string str) public view returns(uint256,address) {
         
         
         
            for(uint i=0;i<=countVar;i++) {
              
                if(keccak256(abi.encodePacked((searchreview[i]))) == keccak256(abi.encodePacked((str)))){
                    return (i,uniquereview[i]);
                }
                
                }
               
            }
             
         }
         